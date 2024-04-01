'use client';

import {
  AdBox,
  BackButton,
  FlexBox,
  Header,
  Input,
  MoneyShow,
  OtherInfoElement,
  TinyButton,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import Link from 'next/link';
import ListDeal from '../_components/ListDeal';
import { useEffect, useState } from 'react';
import BookApis from '../../_apis';
import { format } from 'date-fns';
import useBookStore from '../../_store';
export default function BookList() {
  const [dayData, setDayData] = useState<TransactionType[]>();
  const [totalIncome, setTotalIncom] = useState();
  const [totalOutcome, setTotalOutcome] = useState();
  const { selectedDate, updateSelectedTransaction } = useBookStore();

  const { getDayBook } = BookApis();

  useEffect(() => {
    console.log(selectedDate && format(selectedDate, 'yyyy-MM-dd'));
    selectedDate &&
      (async function () {
        const res = await getDayBook({
          date: format(selectedDate, 'yyyy-MM-dd'),
          childUuid: null,
        });
        console.log(res);
        setDayData(res?.data?.TransactionDetails);
      })();
  }, [selectedDate]);

  return (
    <div>
      <div className="p-[1.2rem] flex flex-col gap-[1rem]">
        <AdBox
          mode={'STATIC'}
          subText={'상세 정보 추가 또는 더치페이 요청을 할 수 있어요!'}
          title={'각 지출 내역을 누르면 선택할 수 있어요 '}
        />
        <MoneyShow
          mode={'DIVIDED'}
          text={['지출', '수입']}
          money={[`${commaNum(-6000)}`, `${commaNum(5000)}`]}
          unit={'원'}
        />
        <div className="w-full flex items-end justify-end pr-[1rem]">
          <Link href={`./post`}>
            <TinyButton label={'직접 추가'} />
          </Link>
        </div>
        {dayData?.map((deal, index) => (
          <Link
            className="cursor-pointer"
            href={`./${deal?.transactionUuid}/detail`}
            key={`deal-${index}`}
            onClick={() => updateSelectedTransaction(deal)}
          >
            <FlexBox
              isDivided={deal?.memo ? true : false}
              mode="LIST"
              topChildren={
                <div>
                  <OtherInfoElement
                    leftExplainText={`${deal.code}`}
                    leftHighlightText={`${deal?.counterpartyName}`}
                    money={`${commaNum(deal.amount)}원`}
                    state={`${deal.amount > 0 ? 'UP' : 'DOWN'}`}
                  />
                  {deal.receipts && <ListDeal deals={deal.receipts} />}
                </div>
              }
              bottomChildren={
                <>
                  {deal.memo && <p className="mt-[-1rem]">{`${deal.memo}`}</p>}
                </>
              }
            />
          </Link>
        ))}
      </div>
    </div>
  );
}
