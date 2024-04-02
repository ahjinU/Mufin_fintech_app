import { BackButton, Header, NavBar } from '@/components';

export default function LoanListLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="relative">
      <div className="min-h-[calc(100dvh-13.1rem)] flex flex-col">
        <Header>
          <BackButton label={'대출 신청 목록'} />
        </Header>
        <div className="p-[1.2rem] min-h-[calc(100vh-13.1rem)]">{children}</div>
        <NavBar mode={'CHILD'} />
      </div>
    </div>
  );
}
