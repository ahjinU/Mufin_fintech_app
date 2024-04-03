import { Button, NavText } from '@/components';
import Image from 'next/image';
import Link from 'next/link';

export default function MainWithoutSignIn() {
  return (
    <div className="h-screen px-[1.2rem]">
      <div className="h-[84%] flex flex-col justify-center items-center gap-[1rem]">
        <div className="relative">
          <Image
            src={'/images/icon-mufin.png'}
            width={160}
            height={50}
            alt={'mufin'}
            className="w-[16rem] m-[2rem]"
          ></Image>
          <div className="absolute -top-7 -right-10">
            <Image
              src={'/images/icon-app-logo.png'}
              width={30}
              height={25}
              alt={'heart'}
              className="w-[3rem] m-[2rem]"
            ></Image>
          </div>
        </div>
        <p className="custom-bold-text text-custom-black">
          나의 첫 경제생활 도우미
        </p>
        <div className="flex flex-col items-center custom-medium-text text-custom-black">
          <p>복잡한 송금, 가계부 관리, 어려운 주식 공부까지</p>
          <p>머핀에서 쉽고 재미있게 시작해 보세요!</p>
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
