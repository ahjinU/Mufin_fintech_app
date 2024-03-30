import Link from 'next/link';
import Image from 'next/image';
import { ChevronRightIcon } from '@heroicons/react/24/solid';
import {
  AdBox,
  FlexBox,
  NavBar,
  ProgressBar,
  SmallButton,
  TinyButton,
} from '@/components';
import MainHeader from './MainHeader';

import { useEffect } from 'react';
import useFetch from '@/hooks/useFetch';
import useUserStore from '../_store/store';

function MainBoxText({
  img,
  subText,
  mainText,
}: {
  img: string;
  subText: string;
  mainText: string;
}) {
  return (
    <div className="flex gap-[1rem] items-center">
      <Image
        src={img}
        width={50}
        height={50}
        alt={mainText}
        className="w-[4rem]"
      />
      <div className="flex flex-col">
        <p className="custom-medium-text text-custom-dark-gray">{subText}</p>
        <p className="custom-semibold-text text-custom-black">{mainText}</p>
      </div>
    </div>
  );
}

export default function Main() {
  const { postFetch } = useFetch();
  const { userData, setUserData } = useUserStore();

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const res = await postFetch({ api: '/user/mypage' });
        setUserData(res.data);
      } catch (error) {
        console.error('사용자 정보 가져오기 에러', error);
      }
    };
    fetchUserData();
  }, [postFetch, setUserData]);

  return (
    <div className="relative">
      <div className="w-full min-h-screen mb-[1rem]">
        <MainHeader></MainHeader>
        <div className="px-[1.2rem] flex flex-col gap-[1rem]">
          <Link
            href="/info"
            className="h-[6.4rem] mt-[0.4rem] pr-[1rem] pl-[1.6rem] rounded-[2rem] bg-custom-light-gray
          flex justify-between items-center"
          >
            <div className="flex gap-[1rem] items-center">
              <p className="custom-bold-text text-custom-black">
                {userData.name}
              </p>
              <div className="size-fit px-[0.8rem] rounded-[8rem] bg-custom-light-purple">
                <p className="custom-light-text text-custom-white">{`${userData.ranking}위`}</p>
              </div>
            </div>
            <ChevronRightIcon className="size-8 fill-custom-medium-gray" />
          </Link>
          <FlexBox
            isDivided={false}
            topChildren={
              <div className="flex justify-between items-center">
                <MainBoxText
                  img="/images/icon-dollar.png"
                  subText={`내 잔액(${userData.accountNumber})`}
                  mainText={`${userData.balance}원`}
                />
                <ChevronRightIcon className="size-8 fill-custom-medium-gray" />
              </div>
            }
            bottomChildren={
              <div className="mt-[0.6rem] flex justify-center gap-[1rem]">
                <Link href="/transfer">
                  <SmallButton mode="ACTIVE" label="송금하기" />
                </Link>
                <Link href="/pay">
                  <SmallButton mode="ACTIVE" label="결제하기" />
                </Link>
              </div>
            }
          />
          <AdBox
            icon="/images/icon-calendar.png"
            link="/book"
            mode="INTERACTIVE"
            subText="영수증을 등록하고 요술램프 혜택을 받아보세요!"
            title="내 가계부에 세부내역 추가하기"
          />
          <FlexBox
            isDivided
            topChildren={
              <div className="px-[0.4rem] flex flex-col gap-[1.4rem]">
                <div className="flex justify-between items-center">
                  <MainBoxText
                    img="/images/icon-card.png"
                    subText="이번 달 쓴 돈"
                    mainText={`${userData.monthAmounts}원`}
                  />
                  <TinyButton label="가계부" />
                </div>
                <div className="flex justify-start items-center">
                  <MainBoxText
                    img="/images/icon-target.png"
                    subText="내가 모은 돈"
                    mainText={`${userData.savings}원`}
                  />
                </div>
              </div>
            }
            bottomChildren={
              <Link
                href="/loan/apply"
                className="flex justify-center items-center gap-[0.4rem]"
              >
                <p className="custom-medium-text text-custom-dark-gray">
                  대출 도움받으러 가기
                </p>
                <ChevronRightIcon className="size-6 fill-custom-medium-gray" />
              </Link>
            }
          />
          <FlexBox
            isDivided
            topChildren={
              <div className="flex justify-between items-center">
                <MainBoxText
                  img="/images/icon-donut-graph.png"
                  subText="내 초코칩 저장소와 주식 평가"
                  mainText={`${userData.chocochip + userData.totalPrice}초코칩`}
                />
                <ChevronRightIcon className="size-8 fill-custom-medium-gray" />
              </div>
            }
            bottomChildren={
              <div className="flex flex-col gap-[2rem]">
                <ProgressBar
                  barGage={
                    parseFloat(
                      (
                        (userData.chocochip /
                          (userData.chocochip + userData.totalPrice)) *
                        100
                      ).toFixed(1),
                    ) || 0
                  }
                  height="h-[2.4rem]"
                  boundary="rounded-none"
                />
                <div className="flex justify-between items-center">
                  <div className="flex gap-[1rem]">
                    <div className="size-[3rem] rounded-full bg-custom-purple"></div>
                    <div>
                      <p className="custom-medium-text text-custom-black">
                        초코칩 저장소
                      </p>
                      <p className="custom-light-text text-custom-dark-gray">
                        {parseFloat(
                          (
                            (userData.chocochip /
                              (userData.chocochip + userData.totalPrice)) *
                            100
                          ).toFixed(1),
                        ) || '0'}
                        %
                      </p>
                    </div>
                  </div>
                  <p className="custom-medium-text text-custom-black">
                    {userData.chocochip}초코칩
                  </p>
                </div>
                <div className="flex justify-between items-center">
                  <div className="flex gap-[1rem]">
                    <div className="size-[3rem] rounded-full bg-custom-light-purple"></div>
                    <div>
                      <p className="custom-medium-text text-custom-black">
                        주식 평가
                      </p>
                      <p className="custom-light-text text-custom-dark-gray">
                        {parseFloat(
                          (
                            (userData.totalPrice /
                              (userData.chocochip + userData.totalPrice)) *
                            100
                          ).toFixed(1),
                        ) || '0'}
                        %
                      </p>
                    </div>
                  </div>
                  <div>
                    <p className="custom-medium-text text-custom-black">
                      {userData.totalPrice}초코칩
                    </p>
                    <p className="custom-light-text text-custom-black">
                      {userData.totalIncome}초코칩({userData.totalIncomePercent}
                      %)
                    </p>
                  </div>
                </div>
              </div>
            }
          />
        </div>
      </div>
      <NavBar mode={userData.isParent ? 'PARENT' : 'CHILD'} />
    </div>
  );
}
