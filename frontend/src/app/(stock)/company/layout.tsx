import { BackButton, Header } from '@/components';

export default function CompanyLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <section className="flex flex-col gap-[1rem]">
      <Header>
        <BackButton label="바람막이 회사" link="/" />
      </Header>
      {children}
    </section>
  );
}
