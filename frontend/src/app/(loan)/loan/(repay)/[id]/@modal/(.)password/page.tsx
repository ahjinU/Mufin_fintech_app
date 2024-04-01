'use client';

import { usePathname, useRouter } from 'next/navigation';
import { PayPasswordBox } from '@/components';
import { useState } from 'react';
import LoanAPI from '@/app/(loan)/loan/_api';
import useLoanRepayStore from '../../_store';

export default function Password() {
  const router = useRouter();
  const { postLoan } = LoanAPI();
  const { paymentCnt } = useLoanRepayStore();

  var currentUrl = usePathname();
  var id = currentUrl?.split('/')[2];

  const handelComfirm = async () => {
    console.log({ loanUuid: id, payment_cnt: paymentCnt });
    const res =
      id &&
      paymentCnt &&
      (await postLoan({ loanUuid: id, payment_cnt: paymentCnt }));
    res.message === '정상적으로 상환되었습니다.' && router.push('loan/list');
  };

  return (
    <div
      className="absolute top-0 left-0 size-full bg-custom-black-with-opacity
    flex justify-center"
    >
      <PayPasswordBox
        mode={'CHECK'}
        handleConfirmButton={handelComfirm}
        handleCloseButton={() => router.back()}
      />
    </div>
  );
}
