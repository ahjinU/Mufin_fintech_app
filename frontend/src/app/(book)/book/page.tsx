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
import useDate from '@/utils/date';
import { format, getDay } from 'date-fns';
import { useEffect, useState } from 'react';
import BookApis from './_apis';
import { getKorDay } from '@/utils/getKorDay';

export default function Book() {
  const { calculateDateRange, currentMonth } = useDate();
  const { startDate, endDate } = calculateDateRange();
  const { getMonthBookDetail } = BookApis();

  const [bookDetail, setBookDetail] = useState<MonthBookDetailType | null>(
    null,
  );

  useEffect(() => {
    (async function () {
      const res = await getMonthBookDetail({
        startDate: format(startDate, 'yyyy-MM-dd'),
        endDate: format(endDate, 'yyyy-MM-dd'),
        childUuid: null,
      });
      console.log(res);
      !bookDetail && setBookDetail(res?.data);
    })();
  }, [startDate, endDate]);

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
              commaNum(bookDetail?.monthOutcome),
              `+${commaNum(bookDetail?.monthIncome)}`,
            ]}
            unit={'원'}
          />
        </ComplexInput>
        <div className="flex flex-col gap-[1.2rem] max-h-[30rem] overflow-y-scroll scrollbar-hidden">
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
