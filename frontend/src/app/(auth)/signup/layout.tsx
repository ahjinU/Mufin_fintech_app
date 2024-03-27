import { Header, BackButton } from '@/components';

export default function Layout({
  children,
  modal,
}: Readonly<{
  children: React.ReactNode;
  modal: React.ReactNode;
}>) {
  return (
    <div className="min-h-screen bg-custom-white">
      <Header>
        <BackButton label="회원가입" />
      </Header>
      <div className="px-[1.2rem]">{children}</div>
      <div className="z-10">{modal}</div>
    </div>
  );
}
