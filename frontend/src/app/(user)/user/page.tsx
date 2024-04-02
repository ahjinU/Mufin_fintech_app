'use client';

import useUserStore from '@/app/_store/store';
import { Button, NavButton } from '@/components';
import { signOut } from 'next-auth/react';

export default function UserMenu() {
  const { userData } = useUserStore();

  const handleClick = async () => {
    await signOut();
    localStorage.removeItem('userStore');
    window.location.href = '/';
  };

  const data = !userData.isParent
    ? [
        ['적금 신청하기', '/savings/apply'],
        ['적금 보기 & 납부하기', '/savings/mine'],
        ['대출 받기', '/loan/apply'],
        ['대출 보기 & 상환하기', '/loan/list'],
      ]
    : [
        ['아이 회원가입하기', '/'],
        ['아이를 위한 적금 상품 만들기', '/savings/list'],
        ['적금 현황 확인하기', '/savings/confirm'],
        ['아이의 대출 요청 확인하기', '/'],
        ['대출 현황 확인하기', '/'],
      ];

  return (
    <section className="p-[1.2rem] flex flex-col gap-[1rem] min-h-[calc(100vh-11.6rem)]">
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
      <div className="fixed bottom-[6rem] left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Button
          mode={'ACTIVE'}
          label={'로그아웃'}
          onClick={handleClick}
        ></Button>
      </div>
    </section>
  );
}
