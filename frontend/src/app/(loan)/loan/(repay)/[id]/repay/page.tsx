'use client';

import {
  AccountBox,
  Button,
  ComplexInput,
  GuideText,
  Select,
} from '@/components';
import { useServerPostFetch } from '@/hooks/useServerFetch';
import { LoanDetailType } from '../../../_types';
import { useEffect, useState } from 'react';
import useFetch from '@/hooks/useFetch';
import Link from 'next/link';

export default function LoanRepay() {
  const [month, setMonth] = useState(1);

  const { postFetch } = useFetch();

  let loan: LoanDetailType = {
    reason: '',
    totalAmount: 0,
    remainderAmount: 0,
    startDate: new Date(),
    remainderDay: '',
    paymentDate: 0,
    overDueCnt: 0,
  };

  // useEffect(() => {
  //   const fetchLoan = async () => {
  //     const res = await postFetch({
  //       data: { loanUuid: '987bafb9-b6f6-4e9a-9ea1-93f4b6d44d3c' },
  //       api: '/loan/detail/child',
  //     });
  //     loan = res?.data;
  //   };

  //   fetchLoan();
  // }, []);

  return (
    <>
      <div className="flex flex-col gap-[1.5rem] pb-[6rem]">
        <GuideText text={'부모님께 빌렸던 돈을 갚아요.'} />
        <AccountBox isGrayBackground text={''} money={0} />
        <ComplexInput
          label={'남은 전체 약속 금액'}
          mode={'NONE'}
          height="h-[7rem]"
        >
          <div className="flex flex-row gap-[0.5rem] custom-semibold-text">
            <p className="text-custom-purple">{loan?.remainderAmount}원</p>/
            <p>{loan?.totalAmount}원</p>
          </div>
        </ComplexInput>
        <ComplexInput
          label={'약속한 1개월 납부 금액'}
          mode={'NONE'}
          height="h-[10rem]"
        >
          <div className="flex flex-row gap-[0.5rem]">? </div>
        </ComplexInput>
        <ComplexInput
          label={'몇개월 분을 납부할까요?'}
          mode={'INFORM'}
          isMsg={true}
          message={`오늘은 ${month}개월분 납부할게요`}
          height="h-[10rem]"
        >
          <Select mode={'SAVINGS'} min={0} max={12} setValue={setMonth} />
        </ComplexInput>
      </div>
      <Link href={'doc'}>
        <Button mode={'ACTIVE'} label={'다음'} />
      </Link>
    </>
  );
}
