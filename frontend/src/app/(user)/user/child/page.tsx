'use client';

import { useRouter } from 'next/navigation';
import { PlusButton } from '@/components';

export default function ChildInfo() {
  const router = useRouter();

  return (
    <div>
      <PlusButton
        label="아이 추가로 회원가입하기"
        onClick={() => router.push('/signup')}
      />
    </div>
  );
}
