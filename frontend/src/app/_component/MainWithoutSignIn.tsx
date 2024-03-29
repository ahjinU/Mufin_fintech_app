import { Button, NavText } from '@/components';
import Image from 'next/image';
import Link from 'next/link';

export default function MainWithoutSignIn() {
  return (
    <div className="h-screen">
      <div className="h-[84%] flex flex-col justify-center items-center gap-[1rem]">
        <Image
          src={'/images/icon-app-logo.png'}
          width={300}
          height={260}
          alt={'mufin'}
          className="w-[8rem] m-[2rem]"
        ></Image>
        <p className="custom-bold-text text-custom-black">
          나의 첫 경제생활 도우미
        </p>
        <div className="flex flex-col items-center custom-medium-text text-custom-black">
          <p>복잡한 송금, 결제, 용돈기입장, 어려운 주식 공부까지</p>
          <p>자스민페이에서 쉽고 재미있게 시작해 보세요!</p>
        </div>
      </div>
      <div className="h-[16%] flex flex-col justify-start items-center gap-[1rem]">
        <Link href="/signup" className="w-full">
          <Button mode="ACTIVE" label="시작하기" />
        </Link>
        <div className="flex gap-[1rem]">
          <p className="custom-light-text">이미 계정이 있나요?</p>
          <NavText link="/signin" text="로그인하기" />
        </div>
      </div>
    </div>
  );
}
