'use client';

import {
  ComplexInput,
  FlexBox,
  Header,
  MoneyShow,
  OtherInfoElement,
} from '@/components';
import Calendar from './_components/Calendar';
import { commaNum } from '@/utils/commaNum';
import { format, getDay } from 'date-fns';
import { useEffect, useState } from 'react';
import BookApis from './_apis';
import { getKorDay } from '@/utils/getKorDay';
import useBookStore from './_store';

export default function Book() {
  const { currentStartDate, currentEndDate } = useBookStore();
  const { getMonthBookDetail } = BookApis();

  const [bookDetail, setBookDetail] = useState<MonthBookDetailType | null>(
    null,
  );

  useEffect(() => {
    (async function () {
      const res = await getMonthBookDetail({
        startDate: format(currentStartDate, 'yyyy-MM-dd'),
        endDate: format(currentEndDate, 'yyyy-MM-dd'),
        childUuid: null,
      });
      setBookDetail(res?.data);
    })();
  }, [currentStartDate, currentEndDate]);

  return (
    <div>
      <Header>
        <h1 className="custom-bold-text">용돈 가계부</h1>
      </Header>
      <div className="p-[1.2rem] flex flex-col gap-[1rem]">
        <Calendar />
        <ComplexInput label={'이번 달 내역'} mode={'NONE'}>
          <MoneyShow
            mode={'DIVIDED'}
            text={['지출', '수입']}
            money={[
              bookDetail?.monthOutcome
                ? commaNum(bookDetail?.monthOutcome)
                : '0',
              bookDetail?.monthIncome
                ? `+${commaNum(bookDetail?.monthIncome)}`
                : '0',
            ]}
            unit={'원'}
          />
        </ComplexInput>
        <div className="flex flex-col gap-[1.2rem] max-h-[30rem] overflow-y-scroll  scrollbar-hide">
          {bookDetail?.transactionDtoList?.map((trans, index) => {
            return (
              <FlexBox
                key={`tarns-${index}`}
                isDivided={false}
                mode="LIST"
                date={`${format(trans?.date, 'd')}일 ${getKorDay(
                  getDay(trans?.date),
                )}`}
                topChildren={
                  <OtherInfoElement
                    leftExplainText={trans?.code}
                    leftHighlightText={trans?.counterpartyName}
                    money={`${commaNum(trans?.amount)}원`}
                    state={trans.amount > 0 ? 'UP' : 'DOWN'}
                  />
                }
              />
            );
          })}
        </div>
      </div>
    </div>
  );
}
