'use client';

import { NavButton } from '@/components';
import { useState, useEffect } from 'react';

export default function UserMenu() {
  const [userType, setUserType] = useState<'CHILD' | 'PARENT'>('CHILD');

  const data =
    userType === 'CHILD'
      ? [
          ['최근 결제 내역 확인하기', '/'],
          ['적금 신청하기', '/savings/apply'],
          ['적금 보기 & 납부하기', '/savings/mine'],
          ['대출 받기', '/'],
          ['대출 보기 & 상환하기', '/'],
        ]
      : [
          ['아이 회원가입하기', '/'],
          ['아이를 위한 적금 상품 만들기', '/savings/list'],
          ['적금 현황 확인하기', '/'],
          ['아이의 대출 요청 확인하기', '/'],
          ['대출 현황 확인하기', '/'],
        ];

  useEffect(() => {
    // 여기에 아이의 유형 불러오기 fetch문
    setUserType('CHILD');
  }, []);

  // 아이와 부모에 따라 다른 메뉴 및 NavBar
  return (
    <section className="p-[1.2rem] flex flex-col gap-[1rem] min-h-screen">
      <NavButton mode="HIGHLIGHT" label="김지니" link="/" />
      {data.map((menu, index) => {
        return (
          <NavButton
            key={`menu-${index}`}
            mode="GENERAL"
            label={menu[0]}
            link={menu[1]}
          />
        );
      })}
    </section>
  );
}
