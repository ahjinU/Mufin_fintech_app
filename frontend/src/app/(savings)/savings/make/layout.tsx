import React from 'react';
import { Header, BackButton } from '@/components';

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <Header>
        <BackButton label="적금 상품 만들기" />
      </Header>
      {children}
    </>
  );
}
