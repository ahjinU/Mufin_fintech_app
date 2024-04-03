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
    router.push('/loan/success');
  };

  return (
    <div
      className={`absolute top-0 left-0 size-full ${
        isOpen && 'bg-custom-black-with-opacity'
      } 
    flex justify-center z-50`}
    >
      <AlertConfirm
        handleClickOkay={useHandleOkay}
        handleClickNo={() => {
          setIsOpen(false);
          router.replace('/');
        }}
        isOpen={isOpen}
        text={`대출을 부모님께 신청할까요? 신청하지 않으면 홈화면으로 돌아가요.`}
      />
    </div>
  );
}
