'use client';

import { AlertConfirm } from '@/components';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import useLoanApplyStore from '../../_store';
import { postLoanApply } from '../../_api/postLoanApply';

export default function Check() {
  const router = useRouter();
  const { apply } = useLoanApplyStore();

  const [isOpen, setIsOpen] = useState<boolean>(true);

  return (
    <div
      className="absolute top-0 left-0 size-full bg-custom-black-with-opacity
    flex justify-center"
    >
      <AlertConfirm
        handleClickOkay={async () => {
          await postLoanApply({ data: apply, api: '/loan/apply' });
          router.replace('/signup/complete');
        }}
        handleClickNo={() => {
          setIsOpen(false);
        }}
        isOpen={isOpen}
        text="대출 신청하시겠어요?"
      />
    </div>
  );
}
