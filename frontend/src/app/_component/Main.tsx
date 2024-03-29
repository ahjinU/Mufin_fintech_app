import Link from 'next/link';
import Image from 'next/image';
import { ChevronRightIcon } from '@heroicons/react/24/solid';
import {
  AdBox,
  Button,
  FlexBox,
  NavBar,
  OtherInfoElement,
  SmallButton,
  TinyButton,
} from '@/components';
import MainHeader from './MainHeader';

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

// 공통 컴포넌트 수정하기 - h, rounded
function ProgressBarGraph({ barGage }: { barGage: number }) {
  return (
    <div className="w-full h-[2.4rem] rounded-[2rem] bg-custom-light-purple overflow-hidden">
      <div
        className="h-full rounded-none bg-custom-purple"
        style={{ width: `${barGage}%` }}
      ></div>
    </div>
  );
}

export default function Main() {
  return (
    <div className="w-full">
      <MainHeader></MainHeader>
      <div className="px-[1.2rem] flex flex-col gap-[1rem]">
        <Link
          href="/info"
          className="h-[6.4rem] mt-[0.4rem] pr-[1rem] pl-[1.6rem] rounded-[2rem] bg-custom-light-gray
          flex justify-between items-center"
        >
          <div className="flex gap-[1rem] items-center">
            <p className="custom-bold-text text-custom-black">김지니</p>
            <div className="size-fit px-[0.8rem] rounded-[8rem] bg-custom-light-purple">
              <p className="custom-light-text text-custom-white">427위</p>
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
                subText="내 잔액(10-1001-40100)"
                mainText="192,000원"
              />
              <ChevronRightIcon className="size-8 fill-custom-medium-gray" />
            </div>
          }
          bottomChildren={
            <div className="mt-[0.6rem] flex justify-center gap-[1rem]">
              <SmallButton mode="ACTIVE" label="송금하기" />
              <SmallButton mode="ACTIVE" label="결제하기" />
            </div>
          }
        />
        <AdBox
          icon="/images/icon-calendar.png"
          link="/"
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
                  mainText="83,000원"
                />
                <TinyButton label="가계부" />
              </div>
              <div className="flex justify-start items-center">
                <MainBoxText
                  img="/images/icon-target.png"
                  subText="내가 모은 돈"
                  mainText="124,000원"
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
                mainText="220,000,000초코칩"
              />
              <ChevronRightIcon className="size-8 fill-custom-medium-gray" />
            </div>
          }
          bottomChildren={
            <div className="flex flex-col gap-[1rem]">
              <ProgressBarGraph barGage={64.8} />
              <div className="">
                <div className=""></div>
              </div>
            </div>
          }
        />
      </div>
    </div>
  );
}
