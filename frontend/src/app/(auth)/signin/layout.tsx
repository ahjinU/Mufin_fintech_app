import { BackButton } from '@/components';

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="min-h-screen px-[1.2rem] bg-custom-white">
      <div className="flex items-center w-full h-[5.6rem]">
        <BackButton label="로그인" link="/" />
      </div>
      {children}
    </div>
  );
}
