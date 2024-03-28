'use client';

import { useRouter } from 'next/navigation';
import { AgreeBottomSheet } from '@/components';
import { useState } from 'react';

export default function Modal() {
  const router = useRouter();

  const [isOpen, setIsOpen] = useState<boolean>(true);

  const handleClose = () => {
    router.replace('/loan/apply');
    setIsOpen(false);
  };

  const handelConfirm = () => {
    router.replace('/loan/apply/detail');
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
        title={'대출 신청 동의'}
        notice={'대출 신청하려면 아래 약관 동의가 필요해요'}
        conditions={['개인 정보 수집 및 이용 동의', '대출 신청 동의']}
        isOpen={isOpen}
        handleClickCloseButton={handleClose}
        handleClickConfirmButton={handelConfirm}
      />
    </div>
  );
}
