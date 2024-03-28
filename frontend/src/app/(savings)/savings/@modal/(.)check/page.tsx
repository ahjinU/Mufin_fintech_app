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
        handleClickOkay={() => {
          router.replace('/savingsList');
        }}
        handleClickNo={() => router.back()}
        isOpen
        text="적금 상품을 삭제하시겠어요?"
      />
    </div>
  );
}
