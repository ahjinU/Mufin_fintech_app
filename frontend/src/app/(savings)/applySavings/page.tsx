'use client';

import { FlexBox, TinyButton } from '@/components';

function Title() {
  return (
    <section>
      <h2 className="custom-medium-text">
        아들아 너는 돈을 넣어라, 나는 이...
      </h2>
      <span className="custom-light-text">적금</span>
    </section>
  );
}

export function ContentRow({
  keyName,
  value,
}: {
  keyName: string;
  value: string;
}) {
  return (
    <section className="flex justify-between custom-medium-text">
      <span>{keyName}</span>
      <span>{value}</span>
    </section>
  );
}

export default function ApplySavings() {
  // const [isBottomSheetOpen, setIsBottomSheetOpen] = useState<boolean>(false);

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] relative">
        <FlexBox
          isDivided={true}
          topChildren={<Title />}
          bottomChildren={
            <>
              <div className="flex flex-col gap-[0.5rem]">
                <ContentRow keyName="이자율" value="0.1%" />
                <ContentRow keyName="적금 기간" value="12개월" />
              </div>
              <div className="self-end">
                <TinyButton label="선택하기" />
              </div>
            </>
          }
        />
      </section>
    </>
  );
}
