'use client';

import Link from 'next/link';
import Image from 'next/image';
import useUserStore from '@/app/_store/store';
import { Button, NavButton, NavText } from '@/components';
import { signOut } from 'next-auth/react';

export default function UserMenu() {
  const { userData } = useUserStore();

  const handleClick = async () => {
    await signOut();
    localStorage.removeItem('userStore');
    window.location.href = '/';
  };

  const menuList = !userData.isParent
    ? [
        ['적금 신청하기', '/savings/apply'],
        ['적금 보기 & 납부하기', '/savings/mine'],
        ['대출 신청하기', '/loan/apply'],
        ['대출 보기 & 상환하기', '/loan/list'],
      ]
    : [
        ['우리 아이 회원가입하기', '/signup'],
        ['아이를 위한 적금 상품 만들기', '/savings/list'],
        ['적금 현황 확인하기', '/savings/confirm'],
        ['아이의 대출 요청 심사하기', '/loan/parent/assesment'],
        ['대출 현황 확인하기', '/loan/parent'],
      ];

  return (
    <section className="flex flex-col gap-[1rem]">
      <div className="w-full h-[12rem] p-[1.6rem] bg-custom-purple text-custom-white flex flex-col justify-between">
        <div className="self-start flex items-center gap-[1rem]">
          <Image
            src="/images/icon-muffin.png"
            width={200}
            height={200}
            alt={userData.name}
            className="size-[4.8rem]"
          />
          <div className="flex flex-col">
            <p className="custom-medium-text">오늘도 좋은 하루되세요!</p>
            <p className="custom-bold-text">{userData.name}</p>
          </div>
        </div>
        <div className="self-end flex gap-[1.4rem] underline custom-light-text">
          {userData.isParent ? <Link href="/child">아이 목록</Link> : null}
          <Link href="/info">내 정보</Link>
          <p onClick={handleClick}>로그아웃</p>
        </div>
      </div>
      <div className="px-[1.2rem] flex flex-col gap-[0.8rem]">
        {menuList.map((menu, index) => {
          return (
            <NavButton
              key={`menu-${index}`}
              mode="GENERAL"
              label={menu[0]}
              link={menu[1]}
            />
          );
        })}
      </div>
    </section>
  );
}
