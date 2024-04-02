'use client';

import { useSession } from 'next-auth/react';
import useUserStore from '../../_store/store';
import { BackButton, Header, NavText } from '@/components';

export default function UserMenuLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const { data: session } = useSession();
  const { userData } = useUserStore();

  return (
    <div className="min-h-screen bg-custom-white">
      {session && userData && userData.isParent ? (
        <section className="flex flex-col">
          <Header>
            <BackButton label="내 아이"></BackButton>
          </Header>
          <div className="px-[1.2rem]">{children}</div>
        </section>
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
