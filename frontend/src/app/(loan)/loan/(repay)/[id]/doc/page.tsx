import { Button, FlexBox, GuideText } from '@/components';

export default function LoanRepayDoc() {
  const userName = '';

  return (
    <div className="flex flex-col gap-[1rem]">
      <GuideText text={`상환 금액을 확인하고, 상환을 진행해요.`} />
      <FlexBox
        isDivided={false}
        topChildren={
          <div className="flex flex-col gap-[3rem] justify-center items-center">
            <p className="custom-semibold-text">대출 상환서</p>
            <span className="custom-medium-text">
              <p>{`나${userName}는 부모님께 빌린돈`}</p>
              <p>{``}</p>
            </span>
          </div>
        }
      />
      <Button mode={'ACTIVE'} label={'동의하기'} />
    </div>
  );
}
