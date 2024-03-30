import { BackButton, Header, NavBar } from '@/components';

export default function LoanListLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div>
      <Header>
        <BackButton label={'대출 신청 목록'} />
      </Header>
      <div className="p-[1.2rem]">{children}</div>
      <NavBar mode={'CHILD'} />
    </div>
  );
}
