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
import { usePathname } from 'next/navigation';
import { format } from 'date-fns';
import { decodingDate } from '@/utils/decodingDate';
export default function BookList() {
  const [dayData, setDayData] = useState<DayData[]>();

  // var currentUrl = usePathname();
  // var dateString = currentUrl && decodeURI(currentUrl?.split('/')[2]);
  // const dateParts =
  //   dateString && dateString.match(/(\d{4})년 (\d{1,2})월 (\d{1,2})일/);

  // const year = dateParts && parseInt(dateParts[1]);
  // const month = dateParts && parseInt(dateParts[2]) - 1;
  // const day = dateParts && parseInt(dateParts[3]);

  // const date = year && month && day && new Date(year, month, day);

  const currentUrl = usePathname();
  const date = currentUrl && decodingDate(currentUrl);

  const { getDayBook } = BookApis();

  useEffect(() => {
    console.log(date && format(date, 'yyyy-MM-dd'));
    (async function () {
      const res = await getDayBook({
        startDate: date && format(date, 'yyyy-MM-dd'),
        endDate: date && format(date, 'yyyy-MM-dd'),
        childUuid: null,
      });
      console.log(res);
      setDayData(res?.data?.TransactionDetails);
    })();
  }, [currentUrl]);

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
