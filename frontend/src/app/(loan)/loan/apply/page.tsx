import { BackButton, Button, GuideText, Header } from '@/components';
import Image from 'next/image';
import Link from 'next/link';
export default function LoanApply() {
  return (
    <div>
      <Header>
        <BackButton label="대출 신청하기" />
      </Header>
      <div className="p-[1.2rem] gap-[5rem] flex flex-col">
        <GuideText text={'부모님께 급하게 돈을 요청할 수 있어요.'} />
        <div className="flex items-center justify-center">
          <Image
            src={'/images/icon-hands.png'}
            width={200}
            height={200}
            alt={'icon-rank-dot'}
            priority
          />
        </div>
        <span className="custom-medium-text">
          긴급한 상황에 부모님께 돈을 요청할 수 있어요!
          <br />
          부모님께 진심을 보여줄 수 있는 3마디면 가능하지만,
          <br />꼭 갚아야 한다는 것을 명심하세요 :)
        </span>
      </div>
      <Link
        className="fixed bottom-0 w-full px-[1.2rem] py-[3rem]"
        href={'apply/agree'}
      >
        <Button mode={'ACTIVE'} label={'신청하기'} />
      </Link>
    </div>
  );
}
