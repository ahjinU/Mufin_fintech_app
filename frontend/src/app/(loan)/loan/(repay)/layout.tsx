import { BackButton, Header, NavBar } from '@/components';

export default function LoanRepayLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div>
      <Header>
        <BackButton label={'대출 상환하기'} />
      </Header>
      <div className="p-[1.2rem]">{children}</div>
    </div>
  );
}
