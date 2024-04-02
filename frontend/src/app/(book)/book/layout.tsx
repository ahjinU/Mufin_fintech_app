'use client';

import type { Metadata } from 'next';
import { NavBar } from '@/components';
import useUserStore from '@/app/_store/store';

export default function BookLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const { userData } = useUserStore();
  return (
    <div className="relative">
      <div className="min-h-[calc(100vh-6rem)]">{children}</div>
      <NavBar mode={userData.isParent ? 'PARENT' : 'CHILD'} />
    </div>
  );
}
