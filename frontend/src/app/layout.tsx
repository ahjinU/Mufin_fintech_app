import type { Metadata } from 'next';
import { MSWComponent } from './_component/MSWComponent';
import AuthContext from '@/context/AuthContext';
import './globals.css';

export const metadata: Metadata = {
  title: 'mufin',
  description: '나의 첫 경제생활 도우미',
  icons: {
    icon: '/images/icon-app-logo.png',
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="ko">
      <body className="bg-custom-white touch-none">
        <AuthContext>
          <MSWComponent />
          <div className="w-dvw h-dvh mx-auto bg-custom-white relative pb-[0.5rem]">
            {children}
          </div>
        </AuthContext>
      </body>
    </html>
  );
}
