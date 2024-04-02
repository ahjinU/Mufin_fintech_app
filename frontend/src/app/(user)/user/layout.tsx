'use client';

import useUserStore from '@/app/_store/store';
import { Header, NavBar } from '@/components';

export default function UserMenuLayout({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  const { userData } = useUserStore();

  return (
    <div className="flex flex-col">
      <Header>
        <p className="pl-[1rem] custom-bold-text text-custom-black">
          전체 메뉴
        </p>
      </Header>
      <div className="min-h-[calc(100vh-13.1rem)]">{children}</div>
      <NavBar mode={userData.isParent ? 'PARENT' : 'CHILD'} />
    </div>
  );
}
