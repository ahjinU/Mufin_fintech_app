'use client';

import useUserStore from '@/app/_store/store';
import { BackButton, Header, NavBar } from '@/components';
import { useSession } from 'next-auth/react';

export default function LoanListLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const { data: session } = useSession();
  const { userData } = useUserStore();
  return (
    <div className="relative">
      <div className="flex flex-col">
        <Header>
          <BackButton label={'대출 신청 목록'} />
        </Header>
        <div className="p-[1.2rem] min-h-[calc(100vh-13.1rem)]">
          {session && userData && children}
        </div>
        <NavBar mode={'CHILD'} />
      </div>
    </div>
  );
}
