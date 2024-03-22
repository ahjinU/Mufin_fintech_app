import Image from 'next/image';

interface OtherInfoElementProps {
  leftExplainText: string;
  leftHighlightText: string;
  rightExplainText?: string;
  rightHighlightText?: string;
  money?: string;
}

export default function MainRanking({
  leftExplainText,
  leftHighlightText,
  rightExplainText,
  rightHighlightText,
  money,
}: OtherInfoElementProps) {
  return (
    <section className="w-full flex justify-between items-center">
      <div className="w-full flex gap-[1rem] items-center">
        <Image
          src={'/images/icon-stock-main-rank.png'}
          width={42}
          height={42}
          alt={leftExplainText}
          className="w-[4.2rem] h-[4.2rem]"
        />
        <div className="flex flex-col justify-between">
          <span className="custom-semibold-text">{leftHighlightText}</span>
          <span className="custom-medium-text">{leftExplainText}</span>
        </div>
      </div>
      <div className="min-w-fit flex flex-col justify-between items-end">
        <span className="custom-medium-text text-custom-purple">
          {rightHighlightText}
        </span>
        <span className={`custom-light-text text-custom-purple`}>
          {rightExplainText}
        </span>
      </div>
    </section>
  );
}
