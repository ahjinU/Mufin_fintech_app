'use client';

import { useSession } from 'next-auth/react';
import useUserStore from '../../../_store/store';
import { NavText } from '@/components';

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const { data: session } = useSession();
  const { userData } = useUserStore();

  return (
    <div className="min-h-screen bg-custom-white">
      {session && userData && userData.isParent ? (
        children
      ) : (
        <div className="flex flex-col items-center">
          <p className="py-[4rem] custom-semibold-text text-custom-black">
            권한이 없습니다
          </p>
          <NavText text="메인으로 돌아가기" link="/" />
        </div>
      )}
    </div>
  );
}
