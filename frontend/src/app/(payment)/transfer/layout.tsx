import { Header, BackButton } from '@/components';

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="bg-custom-white">
      <Header>
        <BackButton label="송금하기" />
      </Header>
      <div className="px-[1.2rem]">{children}</div>
    </div>
  );
}
