'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { AgreeBottomSheet } from '@/components';

export default function Terms() {
  const [isOpen, setIsOpen] = useState(true);
  const router = useRouter();

  const handleClose = () => {
    router.back();
    setIsOpen(false);
  };

  const handelConfirm = () => {
    router.replace('/account/complete');
    // router.push('/account/password');
    setIsOpen(false);
  };

  return (
    <div
      className={`${
        isOpen &&
        'absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center'
      }`}
    >
      <AgreeBottomSheet
        title={'계좌 개설 동의'}
        notice={'계좌를 개설하려면 아래 약관 동의가 필요해요'}
        conditions={['개인 정보 수집 및 이용 동의', '결제 수단 개설 동의']}
        isOpen={isOpen}
        handleClickCloseButton={handleClose}
        handleClickConfirmButton={handelConfirm}
      />
    </div>
  );
}
