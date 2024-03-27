'use client';

import { useRouter } from 'next/navigation';
import AlertConfirm from '@/components/AlertConfirmModal/AlertConfirmModal';

export default function Check() {
  const router = useRouter();

  return (
    <div
      className="absolute top-0 left-0 size-full bg-custom-black-with-opacity
    flex justify-center"
    >
      <AlertConfirm
        handleClickOkay={() => router.replace('/signup/complete')}
        handleClickNo={() => {}}
        isOpen
        text="회원가입을 진행하시겠어요?"
      />
    </div>
  );
}
