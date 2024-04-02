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
      <body className="bg-custom-black-with-opacity">
        <AuthContext>
          <MSWComponent />
          <div className="size-full mx-auto bg-custom-white relative">
            {children}
          </div>
        </AuthContext>
      </body>
    </html>
  );
}
