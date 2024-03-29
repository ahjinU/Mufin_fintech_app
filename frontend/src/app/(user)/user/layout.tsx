import { Header, NavBar } from '@/components';

export default function UserMenuLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <section className="flex flex-col">
      <Header>
        <h1 className="custom-bold-text text-custom-black">전체 메뉴</h1>
      </Header>
      {children}
      <NavBar mode="CHILD" />
    </section>
  );
}
