import Image from 'next/image';
import { GuideText } from '@/components';

export default function OpenAccount() {
  return (
    <div className="flex flex-col gap-[1rem]">
      <GuideText text="계좌를 개설해주세요" />
      <div className="w-full h-[28.4rem] rounded-[2rem] bg-custom-light-gray">
        {/* <Image src={} width={} height={} alt=''></Image> */}
      </div>
    </div>
  );
}
