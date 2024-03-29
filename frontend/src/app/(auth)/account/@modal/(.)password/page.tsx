'use client';

import { useRouter } from 'next/navigation';
import { PayPasswordBox } from '@/components';

export default function Password() {
  const router = useRouter();

  return (
    <div
      className="absolute top-0 left-0 size-full bg-custom-black-with-opacity
    flex justify-center"
    >
      <PayPasswordBox
        handleConfirmButton={() => router.push('/account/complete')}
        handleCloseButton={() => router.back()}
      />
    </div>
  );
}
