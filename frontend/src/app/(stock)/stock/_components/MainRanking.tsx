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
}: OtherInfoElementProps) {
  return (
    <section className="w-full flex justify-between items-center min-h-[calc(100vh-13.1rem)]">
      <div className="w-full flex items-center gap-[1rem]">
        <Image
          src={'/images/icon-stock-main-rank.png'}
          width={42}
          height={42}
          alt={leftExplainText}
          className="w-[4.2rem] h-[4.2rem]"
        />
        <div className="w-full grid grid-cols-2">
          <span className="custom-medium-text">{leftHighlightText}</span>
          <span className="custom-medium-text text-custom-purple text-right">
            {rightHighlightText}
          </span>
          <span className="custom-light-text">{leftExplainText}</span>
          <span
            className={`custom-light-text text-custom-light-purple text-right`}
          >
            {rightExplainText}
          </span>
        </div>
      </div>
    </section>
  );
}
