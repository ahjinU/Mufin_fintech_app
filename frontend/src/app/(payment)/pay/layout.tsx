import { Header, BackButton } from '@/components';

export default function Layout({
  children,
  modal,
}: Readonly<{
  children: React.ReactNode;
  modal: React.ReactNode;
}>) {
  return (
    <div className="min-h-screen bg-custom-light-gray">
      <Header>
        <BackButton label="결제하기" />
      </Header>
      <div className="px-[1.2rem]">{children}</div>
      {modal}
    </div>
  );
}
