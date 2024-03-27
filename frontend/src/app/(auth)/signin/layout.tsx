import { Header, BackButton } from '@/components';

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="min-h-screen bg-custom-white">
      <Header>
        <BackButton label="로그인" />
      </Header>
      <div className="px-[1.2rem]">{children}</div>
    </div>
  );
}
