import Image from 'next/image';
import { Button, GuideText } from '@/components';
import Link from 'next/link';

export default function OpenAccount() {
  return (
    <div className="flex flex-col gap-[1rem]">
      <GuideText text="계좌를 개설해주세요" />
      <div
        className="w-full h-[28.4rem] p-[2rem] rounded-[2rem] bg-custom-light-gray
      flex flex-col justify-center items-center"
      >
        <Image
          src={'/images/icon-account.png'}
          width={500}
          height={260}
          alt="스님은행"
          className="w-[30rem]"
        ></Image>
        <div className="text-custom-black">
          <p className="custom-semibold-text mb-2">스님은행</p>
          <p className="custom-medium-text">
            스님은행은 아이들이 서비스에서 사용할 수 있는 계좌를 개설하고
            있어요.
          </p>
          <p className="custom-medium-text">
            아래 약관을 잘 살펴보시고, 동의해주세요.
          </p>
        </div>
      </div>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Link href="/account/terms">
          <Button label="약관 보기" mode="ACTIVE" />
        </Link>
      </div>
    </div>
  );
}
