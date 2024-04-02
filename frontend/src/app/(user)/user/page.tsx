'use client';

import useUserStore from '@/app/_store/store';
import { NavButton } from '@/components';

export default function UserMenu() {
  const { userData } = useUserStore();

  const data = !userData.isParent
    ? [
        ['최근 결제 내역 확인하기', '/'],
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
    <section className="p-[1.2rem] flex flex-col gap-[1rem] min-h-[calc(100vh-12rem)]">
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
