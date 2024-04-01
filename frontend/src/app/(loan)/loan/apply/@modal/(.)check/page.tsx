'use client';

import { AlertConfirm } from '@/components';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import useLoanApplyStore from '../../_store';
import useFetch from '@/hooks/useFetch';

export default function Check() {
  const router = useRouter();
  const { apply } = useLoanApplyStore();
  const { postFetch } = useFetch();

  const [isOpen, setIsOpen] = useState<boolean>(true);

  const useHandleOkay = async () => {
    await postFetch({ data: apply, api: '/loan/apply' });
    setIsOpen(false);
    router.replace('/loan/apply/success');
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
        text="대화 내용은 부모님께 전달돼요. 대출을 신청할까요?"
      />
    </div>
  );
}
