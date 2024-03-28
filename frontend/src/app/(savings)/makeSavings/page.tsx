import { GuideText, Button } from '@/components';
import Image from 'next/image';

export default function MakeSavings() {
  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[3rem]">
        <GuideText text="적금 상품을 직접 만들 수 있어요!" />

        <div className="mx-auto">
          <Image
            src={'/images/icon-piggy-bankz.png'}
            width={200}
            height={200}
            alt={'돼지 저금통'}
            className="w-[20rem] h-[20rem]"
          />
        </div>

        <p className="custom-medium-text">
          직접 만든 적금 상품은 아이들이 신청할 수 있어요!
        </p>
        <p className="custom-medium-text">
          아이들이 은행에 일정 기간 돈을 빌려주고, 이자를 얻어 나중에 돌려받을
          수 있는 습관을 들일 수 있죠.
        </p>
      </section>

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button mode="ACTIVE" label="만들기" />
      </div>
    </>
  );
}
