/*
 *  This file is part of the Haven & Hearth game client.
 *  Copyright (C) 2009 Fredrik Tolf <fredrik@dolda2000.com>, and
 *                     Björn Johannessen <johannessen.bjorn@gmail.com>
 *
 *  Redistribution and/or modification of this file is subject to the
 *  terms of the GNU Lesser General Public License, version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  Other parts of this source tree adhere to other copying
 *  rights. Please see the file `COPYING' in the root directory of the
 *  source tree for details.
 *
 *  A copy the GNU Lesser General Public License is distributed along
 *  with the source tree of which this file is a part in the file
 *  `doc/LPGL-3'. If it is missing for any reason, please see the Free
 *  Software Foundation's website at <http://www.fsf.org/>, or write
 *  to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 *  Boston, MA 02111-1307 USA
 */

package haven.glsl;

public class Cons {
    public static Statement stmt(Expression e) {return(Statement.expr(e));}

    public static LBinOp.Assign ass(LValue l, Expression r)   {return(new LBinOp.Assign(l, r));}
    public static LBinOp.Assign ass(Variable l, Expression r) {return(ass(l.ref(), r));}

    public static Add add(Expression... terms) {return(new Add(terms));}
    public static Mul mul(Expression... terms) {return(new Mul(terms));}
    public static BinOp.Sub sub(Expression l, Expression r) {return(new BinOp.Sub(l, r));}
    public static BinOp.Div div(Expression l, Expression r) {return(new BinOp.Div(l, r));}
    public static LBinOp.AAdd aadd(LValue l, Expression r) {return(new LBinOp.AAdd(l, r));}
    public static LBinOp.ASub asub(LValue l, Expression r) {return(new LBinOp.ASub(l, r));}
    public static LBinOp.AMul amul(LValue l, Expression r) {return(new LBinOp.AMul(l, r));}
    public static LBinOp.ADiv adiv(LValue l, Expression r) {return(new LBinOp.ADiv(l, r));}
    public static BinOp.Div inv(Expression op) {return(div(l(1.0), op));}

    public static PreOp.Neg   neg(Expression op) {return(new PreOp.Neg(op));}
    public static LPreOp.Inc  incl(LValue op)    {return(new LPreOp.Inc(op));}
    public static LPreOp.Dec  decl(LValue op)    {return(new LPreOp.Dec(op));}
    public static LPostOp.Inc linc(LValue op)    {return(new LPostOp.Inc(op));}
    public static LPostOp.Dec ldec(LValue op)    {return(new LPostOp.Dec(op));}

    public static BinOp.Eq eq(Expression l, Expression r) {return(new BinOp.Eq(l, r));}
    public static BinOp.Ne ne(Expression l, Expression r) {return(new BinOp.Ne(l, r));}
    public static BinOp.Lt lt(Expression l, Expression r) {return(new BinOp.Lt(l, r));}
    public static BinOp.Gt gt(Expression l, Expression r) {return(new BinOp.Gt(l, r));}
    public static BinOp.Le le(Expression l, Expression r) {return(new BinOp.Le(l, r));}
    public static BinOp.Ge ge(Expression l, Expression r) {return(new BinOp.Ge(l, r));}
    public static BinOp.Or or(Expression l, Expression r) {return(new BinOp.Or(l, r));}
    public static BinOp.And and(Expression l, Expression r) {return(new BinOp.And(l, r));}

    public static LPick pick(LValue val, String el)     {return(new LPick(val, el));}
    public static Pick  pick(Expression val, String el) {return(new Pick(val, el));}

    public static LFieldRef fref(LValue val, String el)     {return(new LFieldRef(val, el));}
    public static FieldRef  fref(Expression val, String el) {return(new FieldRef(val, el));}

    public static Index idx(Expression val, Expression idx) {return(new Index(val, idx));}

    public static IntLiteral   l(int val)    {return(new IntLiteral(val));}
    public static FloatLiteral l(double val) {return(new FloatLiteral(val));}

    public static Vec4Cons vec4(Expression... els) {return(new Vec4Cons(els));}
    public static Vec3Cons vec3(Expression... els) {return(new Vec3Cons(els));}
    public static Vec2Cons vec2(Expression... els) {return(new Vec2Cons(els));}
    public static Mat3Cons mat3(Expression... els) {return(new Mat3Cons(els));}

    public static Expression length(Expression x) {return(Function.Builtin.length.call(x));}
    public static Expression normalize(Expression x) {return(Function.Builtin.normalize.call(x));}
    public static Expression distance(Expression x, Expression y) {return(Function.Builtin.distance.call(x, y));}
    public static Expression dot(Expression x, Expression y) {return(Function.Builtin.dot.call(x, y));}
    public static Expression pow(Expression x, Expression y) {return(Function.Builtin.pow.call(x, y));}
    public static Expression cross(Expression x, Expression y) {return(Function.Builtin.cross.call(x, y));}
    public static Expression reflect(Expression x, Expression y) {return(Function.Builtin.reflect.call(x, y));}
    public static Expression texture2D(Expression s, Expression c) {return(Function.Builtin.texture2D.call(s, c));}
    public static Expression textureCube(Expression s, Expression c) {return(Function.Builtin.textureCube.call(s, c));}
    public static Expression mix(Expression x, Expression y, Expression a) {return(Function.Builtin.mix.call(x, y, a));}
    public static Expression clamp(Expression x, Expression a, Expression b) {return(Function.Builtin.clamp.call(x, a, b));}

    public static Expression reduce(Function fun, Expression... es) {
	if(es.length < 1)
	    throw(new IllegalArgumentException("args < 1"));
	else if(es.length == 1)
	    return(es[0]);
	else
	    return(fun.call(es[0], reduce(fun, haven.Utils.splice(es, 1))));
    }

    public static Expression min(Expression... es) {return(reduce(Function.Builtin.min, es));}
    public static Expression max(Expression... es) {return(reduce(Function.Builtin.max, es));}
}