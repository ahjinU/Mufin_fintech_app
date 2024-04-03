'use client';

import {
  AccountBox,
  Button,
  ComplexInput,
  GuideText,
  Select,
} from '@/components';
import { serverPostFetch } from '@/hooks/useServerFetch';
import { LoanDetailType } from '../../../_types';
import { useEffect, useState } from 'react';
import useFetch from '@/hooks/useFetch';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import LoanAPI from '../../../_api';
import { commaNum } from '@/utils/commaNum';
import { comma } from 'postcss/lib/list';
import useLoanRepayStore from '../_store';

export default function LoanRepay() {
  const { paymentCnt, updatePaymentCnt } = useLoanRepayStore();

  var currentUrl = usePathname();
  var id = currentUrl?.split('/')[2];

  const { getLoanDetail } = LoanAPI();
  const [loan, setLoan] = useState<LoanDetailType>();

  useEffect(() => {
    (async function () {
      const res = id && (await getLoanDetail({ loanUuid: id }));
      setLoan(res?.data);
    })();
  }, [id]);

  return (
    <>
      <div className="flex flex-col gap-[1.5rem] pb-[6rem]">
        <GuideText text={'부모님께 빌렸던 돈을 갚아요.'} />
        <AccountBox isGrayBackground />
        <ComplexInput
          label={'남은 전체 약속 금액'}
          mode={'NONE'}
          height="h-[7rem]"
        >
          <div className="flex flex-row gap-[0.5rem] custom-semibold-text">
            <p className="text-custom-purple">
              {commaNum(loan?.remainderAmount)}원
            </p>
            /<p>{commaNum(loan?.totalAmount)}원</p>
          </div>
        </ComplexInput>
        <ComplexInput
          label={'약속한 1개월 납부 금액'}
          mode={'NONE'}
          height="h-[10rem]"
        >
          <div className="flex flex-row gap-[0.5rem] custom-semibold-text">
            <p>{commaNum(loan?.paymentAmount)}원</p>{' '}
          </div>
        </ComplexInput>
        <ComplexInput
          label={'몇개월 분을 납부할까요?'}
          mode={'INFORM'}
          isMsg={true}
          message={`오늘은 ${paymentCnt}개월분 ${
            loan && commaNum(paymentCnt * loan?.paymentAmount)
          }원 납부할게요`}
          height="h-[10rem]"
        >
          <Select
            mode={'SAVINGS'}
            min={1}
            max={
              (loan?.remainderAmount &&
                loan?.paymentAmount &&
                loan?.remainderAmount / loan?.paymentAmount) ||
              12
            }
            setValue={updatePaymentCnt}
          />
        </ComplexInput>
      </div>
      <div className="fixed bottom-0 inset-x-0 px-[1.2rem] py-[3rem]">
        <Link href={'doc'}>
          <Button mode={'ACTIVE'} label={'다음'} />
        </Link>
      </div>
    </>
  );
}
