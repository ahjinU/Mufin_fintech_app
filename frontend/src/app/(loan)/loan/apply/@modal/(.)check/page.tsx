'use client';

import { AlertConfirm } from '@/components';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import useLoanApplyStore from '../../_store';
import useFetch from '@/hooks/useFetch';

export default function Check() {
  const router = useRouter();
  const { apply } = useLoanApplyStore();
  const { usePostFetch } = useFetch();

  const [isOpen, setIsOpen] = useState<boolean>(true);

  const useHandleOkay = async () => {
    await usePostFetch({ data: apply, api: '/loan/apply' });
    router.replace('/signup/complete');
  };

  return (
    <div
      className="absolute top-0 left-0 size-full bg-custom-black-with-opacity
    flex justify-center"
    >
      <AlertConfirm
        handleClickOkay={useHandleOkay}
        handleClickNo={() => {
          setIsOpen(false);
        }}
        isOpen={isOpen}
        text="대출 신청하시겠어요?"
      />
    </div>
  );
}
