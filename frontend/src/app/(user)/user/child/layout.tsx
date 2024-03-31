import { Header } from '@/components';

export default function UserMenuLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <section className="flex flex-col">
      <Header>
        <h1 className="custom-bold-text text-custom-black">내 아이</h1>
      </Header>
      {children}
    </section>
  );
}
