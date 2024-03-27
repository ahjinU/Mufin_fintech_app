import { BackButton, Header } from '@/components';

export default function CompanyLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <section className="flex flex-col">
      <Header>
        <BackButton label="바람막이 회사" />
      </Header>
      {children}
    </section>
  );
}
