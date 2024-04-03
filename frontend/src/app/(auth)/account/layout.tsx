'use client';

import useUserStore from '../../_store/store';
import { useSession } from 'next-auth/react';
import { Header, BackButton, NavText } from '@/components';

export default function Layout({
  children,
  modal,
}: Readonly<{
  children: React.ReactNode;
  modal: React.ReactNode;
}>) {
  const { data: session } = useSession();
  const { userData } = useUserStore();

  console.log(session)

  return (
    <div className="min-h-screen bg-custom-white">
      {userData.accountNumber.length == 0 ? (
        <>
          <Header>
            <BackButton label="계좌개설" />
          </Header>
          <div className="px-[1.2rem]">{children}</div>
          {modal}
        </>
      ) : (
        <div className="flex flex-col items-center">
          <p className="py-[4rem] custom-semibold-text text-custom-black">
            권한이 없거나 이미 계좌를 개설한 계정입니다.
          </p>
          <NavText text="메인으로 돌아가기" link="/" />
        </div>
      )}
    </div>
  );
}
