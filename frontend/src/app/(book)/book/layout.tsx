'use client';

import { NavBar } from '@/components';
import useUserStore from '@/app/_store/store';
import { useSession } from 'next-auth/react';

export default function BookLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const { data: session } = useSession();

  const { userData } = useUserStore();
  return (
    <div className="relative">
      <div className="min-h-[calc(100vh-7.5rem)]">{session && children}</div>
      <NavBar mode={userData.isParent ? 'PARENT' : 'CHILD'} />
    </div>
  );
}
