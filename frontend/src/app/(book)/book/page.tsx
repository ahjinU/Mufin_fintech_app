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
import { format } from 'date-fns';
import { useEffect } from 'react';
import BookApis from './_apis';

export default function Book() {
  const { calculateDateRange } = useDate();
  const { startDate, endDate } = calculateDateRange();
  const { postMonthBook } = BookApis();

  useEffect(() => {
    (async function () {
      const data = await postMonthBook({
        startDate: format(startDate, 'yyyy-MM-dd'),
        endDate: format(endDate, 'yyyy-MM-dd'),
        childUuid: null,
      });
      console.log(data);
    })();
  }, [endDate, startDate]);

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
            money={[commaNum(-92000), commaNum(-92000)]}
            unit={'원'}
          />
        </ComplexInput>
        <FlexBox
          isDivided={false}
          mode="LIST"
          date="5일 오늘"
          topChildren={
            <OtherInfoElement
              leftExplainText={'화장품'}
              leftHighlightText={'씨제이올리브영 주식회사'}
              money={`${commaNum(3000)}원`}
              state={'UP'}
            />
          }
        />
        <FlexBox
          isDivided={false}
          mode="LIST"
          date="5일 오늘"
          topChildren={
            <OtherInfoElement
              leftExplainText={'화장품'}
              leftHighlightText={'씨제이올리브영 주식회사'}
              money={`${commaNum(3000)}원`}
              state={'UP'}
            />
          }
        />
      </div>
    </div>
  );
}
